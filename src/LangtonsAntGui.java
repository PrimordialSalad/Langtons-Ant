import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.BitSet;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LangtonsAntGui
{
// The main frame component of my GUI.
    private JFrame m_frame;

//	The column Count of the grid.
    private int m_columnCount;
//	The row count of the grid
    private int m_rowCount;
//	The instantiation of the ant class.
    private LangtonsAnt m_ant;
//	The instantiation of the grid renderer class.
    private LangtonsAntGridRenderer m_grid;

//	The JButton "Clear".
    private JButton m_butClear;
//	The JButton "Go".
    private JButton m_butGo;
//	The JButton "Stop".
    private JButton m_butStop;

    public LangtonsAntGui(LangtonsAnt ant, int givenColumnCount, int givenRowCount)
    {
//	The columnCount is set to what is passed in from the ant class.
      m_columnCount = givenColumnCount;
//	The rowCount is set to what is passed in from the ant class.
    	m_rowCount = givenRowCount;
//	The gameOfLife instantiation is set to the ant passing it itself.
    	m_ant = ant;

//	The grid renderer class instantiation is set to a new instance of that class.
    	m_grid = new LangtonsAntGridRenderer(this, m_columnCount, m_rowCount);

//	The frame is set as a new JFrame.
    	m_frame = new JFrame("GameOfLife");

//	This is where the x-button on the window is detailed. The window closes when that button is clicked.
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	The frame's border is set here to a border layout.
        m_frame.setLayout(new BorderLayout());

//	The grid is added to the frame.
        m_frame.add(m_grid, BorderLayout.CENTER);

//	The "Go" button is created and it is added as a new action listener.
        m_butGo = new JButton("Go");
        m_butGo.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does these functions.
        		m_ant.setRun(true);
        	}
        });

//	The "Clear" button is created and it is added as a new action listener.
        m_butClear = new JButton("Clear");
        m_butClear.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does these functions.
        		m_ant.clearGrid();
        		m_grid.repaint();
                m_ant.moveAnt();
        	}
        });

//	The "Stop button is created and it is added as a new action listener.
        m_butStop = new JButton("Stop");
        m_butStop.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
//		When the button is clicked it does this function.
        		m_ant.setRun(false);
        	}
        });

//	The "Go" button is added to the frame.
        m_frame.add(m_butGo, BorderLayout.NORTH);
//	The "Clear" button is added to the frame.
        m_frame.add(m_butClear, BorderLayout.SOUTH);
//	The "Stop" button is added to the frame.
        m_frame.add(m_butStop, BorderLayout.WEST);

//	A function to initiate the frame.
        m_frame.pack();
//	Sets the frame in the middle of the screen.
        m_frame.setLocationRelativeTo(null);
//	Sets the frame visible to the user.
        m_frame.setVisible(true);

//	This event queue is used to catch any exceptions that are created when the GUI is created.
       EventQueue.invokeLater(new Runnable()
       {
            @Override
            public void run()
            {
                try
                {
//		This is used to set the look of the frame to one similar to the OS window's.
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
                {

                }
            }
        });
    }

    public void unsetCell(int row, int column)
    {
//	This is used to unset a cell at a given row and column.  Used in conjunction with set cell.
    	m_ant.unsetCell(row, column);
    }

    public void setCell(int row, int column)
    {
//	This is used to set a cell at a given row and column.  Used in conjunction with unset cell.
    	m_ant.setCell(row, column);
    }

    public void setCellData(BitSet data)
    {
//	This sets the data of the BitSet in the ant class.
    	m_ant.setData(data);
    }

    public void toggleCell(Point cellToToggle)
    {
//	This toggles a single cell in accordance with the point that is given.
    	m_ant.toggleCell(cellToToggle);
    }

    public BitSet getCellData()
    {
//	This gets the data BitSet to be used in classes like the GridRenderer and the SpritePanel.
    	return m_ant.getData();
    }

    public void refresh()
    {
//	This causes the grid to redraw itself.  Refreshing the view of the grid.
    	m_grid.repaint();
    }

    public int giveAntIndex()
    {
//	This transfers the data to the grid renderer class.  From the RedAnt class.
	   return m_ant.giveAntIndex();
    }
}
