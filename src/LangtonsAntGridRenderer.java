import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.BitSet;

import javax.swing.JPanel;



public class LangtonsAntGridRenderer extends JPanel
{
//	This point tracks which cell the mouse is hovering over.
    private Point m_mouseOverCell = null;
//	This is the column count of the grid.
    private int m_columnCount;
//	This is the row count of the grid.
    private int m_rowCount;
//	This is the instantiation of the ant GUI class.
    private LangtonsAntGui m_antGui;
//	This is the variable for the mode.  This controls what sprite is to be drawn.
    private int m_mode = 0;


    public LangtonsAntGridRenderer(LangtonsAntGui antGui, int columnCount, int rowCount)
    {
//	This is where the column count passed in by the GUI class is saved.
    	m_columnCount = columnCount;
//	This is where the row count passed in by the GUI class is saved.
    	m_rowCount = rowCount;
//	This is where the instantiation is set by the GUI class passing itself in.
    	m_antGui = antGui;

//	This is the mouse adapter responsible for the hovering feature.
    	MouseAdapter mouseMotion = new MouseAdapter()
    			{
    				public void mouseMoved(MouseEvent m)
    				{
//				Used to find the width of the grid.
    					int width = getWidth();
//				Used to find the height of the grid.
    					int height = getHeight();

//				Used to calculate the cell width.
    					int cellWidth = width / m_columnCount;
//				Used to calculate the cell height.
    					int cellHeight = height / m_rowCount;

//				Used to figure out the xoffset of the cells.
    					int xOffset = (width - (m_columnCount * cellWidth)) / 2;
//				Used to figure out the yoffset of the cells.
    					int yOffset = (height - (m_rowCount * cellHeight)) / 2;

//				This is where the point is set as null.
    					m_mouseOverCell = null;
    					if(m.getX() >= xOffset && m.getY() >= yOffset)
    					{
//					Used to figure out what cell the mouse is in.
    						int column = (m.getX() - xOffset) / cellWidth;
    						int row = (m.getY() - yOffset) / cellHeight;

    						if(column >= 0 && row >= 0 && column < m_columnCount && row < m_rowCount)
    						{
//						This is where the point is set, the cell's column and row.
    							m_mouseOverCell = new Point(column, row);
    						}
    					}
//					This is where the grid repaints itself.
    					repaint();
    				}
    			};
//			Adds a mouse motion listener.
    			addMouseMotionListener(mouseMotion);
    }

    public Dimension getPreferredSize()
    {
//	Returns the dimensions of the panel, used to draw the grid.
    	return new Dimension(700, 700);
    }

    public void invalidate()
    {
//	Causes the cells to become invalid.
    	m_mouseOverCell = null;
    	super.invalidate();
    }

    public void paintComponent(Graphics g)
    {
//	The function responsible for painting the grid.
    	super.paintComponent(g);
//	This is a Java API class used to create 2D graphics.
    	Graphics2D g2d = (Graphics2D) g.create();

//	This is where the cell data comes to, to be drawn.
    	final BitSet cells = m_antGui.getCellData();

//	Asks for the ant index.
    	final int antIndex = m_antGui.giveAntIndex();

///////	Same as above/////////
    	int width = getWidth();
    	int height = getHeight();

    	int cellWidth = width / m_columnCount;
    	int cellHeight = height / m_rowCount;

    	int xOffset = (width - (m_columnCount * cellWidth)) / 2;
    	int yOffset = (height - (m_rowCount * cellHeight)) / 2;
///////Same as above///////


//	Checks the size of the passed in BitSet is the same size.
    	if(cells.size() >= m_columnCount * m_rowCount)
    	{
    		for(int r = 0; r < m_rowCount; r++)
    		{
    			for(int c = 0; c < m_columnCount; c++)
    			{
    //			The two for loops to cycle through the entirety of the grid.
    //			The 1D index from the 2D for loops.
        				final int index = (r * m_columnCount) + c;

        				if(cells.get(index))
        				{
    //				If the cells are true, paint them black.
    						g2d.setColor(Color.BLACK);
        				}
        				else
        				{
    //				If the cells are false, then paint them white.
        					g2d.setColor(Color.WHITE);
        				}

    				    if(index == antIndex)
      				    {
    //    					Checks if the index is the same as the index for the ant.
    //    					If it is then set it red.
        					  g2d.setColor(Color.RED);
      				    }


        				if(m_mouseOverCell != null)
        				{
    //				If the mouse is hovering over a cell, find the index.
    	    				final int selectedIndex = m_mouseOverCell.x + (m_mouseOverCell.y * m_columnCount);

    	    				if(selectedIndex == index)
    	    				{
    //				Sets the cell that the mouse is hovering over to green, momentarily.
    	    					g2d.setColor(Color.GREEN);
    	    				}
        				}
    //				Creates the rectangles which are the cells.
        				Rectangle cell = new Rectangle(
    							xOffset + (c * cellWidth),
    							yOffset + (r * cellHeight),
    							cellWidth,
    							cellHeight);
    //				Fills the cells.
    					g2d.fill(cell);
    //				Sets the cells borders of the cells to a grey.
        				g2d.setColor(Color.GRAY);
    //				Draws the cells.
        				g2d.draw(cell);
    				}
    			}
    		}
//	    Deletes this edition of the graphics component.
        g2d.dispose();
    	}
    }
