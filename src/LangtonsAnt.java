import java.awt.Point;
import java.util.BitSet;
import java.util.concurrent.TimeUnit;

public class LangtonsAnt
{
//	The grid's column count
	private int m_columnCount = 70;
//	The grid's row count
	private int m_rowCount = 70;
//	The BitSet which represents the board.
	private BitSet m_data = new BitSet(m_columnCount * m_rowCount);
//	The instantiation of the gui class.
	private LangtonsAntGui m_newGui = new LangtonsAntGui(this, m_columnCount, m_rowCount);
//	The instantiation of the generator class.
	private StepGenerator m_newGenerator = new StepGenerator(m_rowCount, m_columnCount);
//	The instantiation of the red AI engine.
	private RedAnt m_redAnt = new RedAnt(this, m_columnCount, m_rowCount);
//	The variable responsible for determining whether the program is exited.  Used by the AIs.
	private boolean m_notDone = true;
//	The variable responsible for the stopping and starting of the while loop.
	private boolean m_run = false;
//	The variable that controls whether or not the board is initially populated
//	with true or false cells.  0 is a completely empty board, anything else is an
//	initially populated board.
	private int m_antMode = 0;


	public static void main(String[] args)
	{
//	Used to run the whole program.  This is the only main function present.
		LangtonsAnt newAnt = new LangtonsAnt();
		newAnt.callToGenerate();
		newAnt.run();
	}
	public void run()
	{
//	This is the loop that is controlled by the buttons.
		while(m_notDone)
		{
			if(m_run)
			{
//			This is used to run the ant.
				runAnt();
//			This is used to repaint after the board has been updated.
				m_newGui.refresh();
			}
			try
			{
//			Used so that the buttons are able to be used during run time.
				TimeUnit.MILLISECONDS.sleep(150);
			}
			catch(InterruptedException e)
			{
//				Thread.currentThread().interrupt();
			}
		}
		m_run = false;
	}

	public void clearGrid()
	{
//	This sets the whole grid to false.  Causing the grid to turn all white.
		m_data.clear();
	}

	public boolean setRun(boolean doRun)
	{
//	This is used to cause the loop to run.  Used by the Go button.
		boolean result = m_run;
		m_run = doRun;
		return result;
	}

	public void callToGenerate()
	{
//	This is the function responsible for initializing the board.
		m_data = (BitSet)(m_newGenerator.generate(m_data, m_antMode).clone());
	}

	public void unsetCell(int row, int column)
	{
//	This is used to unset a certain cell.  Used in conjunction with set cell.
		final int cellToUnsetIndex = column + (row * m_columnCount);

		m_data.clear(cellToUnsetIndex);
	}

    public void setCell(int row, int column)
    {
//	Used to set the cell.  Used in conjunction with unset cell.
    	final int cellToSetIndex = column + (row * m_columnCount);

    	m_data.set(cellToSetIndex);
    }
// Toggles the cell at the given 1D index on or off.
	public void toggleCell(int index)
	{
		m_data.flip(index);
	}

    public void toggleCell(Point cellToToggle)
    {
//	This is used to toggle a certain point on and off.
    	final int cellToToggleIndex = cellToToggle.x + (cellToToggle.y * m_columnCount);

    	m_data.flip(cellToToggleIndex);
    }

    public BitSet getData()
    {
//	Used to return the board data that is housed in this class.
    	return m_data;
    }

    public void setData(BitSet dataToSet)
    {
//	Used to set the board data that is housed in this class.
    	m_data = dataToSet;
    }

    public void runAnt()
    {
//	Used so the call to both run functions are compacted and not spread out everywhere.
			m_redAnt.run(m_data);
    }

    public int giveAntIndex()
    {
//	Gives the data of the antIndex from the Red Ant class.
			return m_redAnt.giveAntIndex();
    }
//	Moves the ant to a new place on the board.
	public void moveAnt()
	{
		m_redAnt.moveAntDueToOutOfBounds();
	}
}
