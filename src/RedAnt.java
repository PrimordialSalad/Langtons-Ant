import java.awt.Point;
import java.util.BitSet;
import java.util.Random;

public class RedAnt
{
//	This is the instantiation of the ant class.
	private LangtonsAnt m_ant;
//	The column count for the grid.
	private int m_columnCount;
//	The row count for the grid.
	private int m_rowCount;
//	This is the random ant index column count.
	private int m_antIndexColumn;
// This is the random ant index row count.
	private int m_antIndexRow;
//	The random variable for use when a random number is needed.
	private Random m_randInt = new Random();

//  The variable that keeps track of which direction the ant is facing.
	private int m_direction; // 0 forward, 1 right, 2 left, 3 backward

	public RedAnt(LangtonsAnt ant, int columnCount, int rowCount)
	{
//	The instantiation of the ant class is set to the ant class passing itself in.
		m_ant = ant;
//	The column count is being set to the value being passed in.
		m_columnCount = columnCount;
//	The row count is being set to the value being passed in.
		m_rowCount = rowCount;

//	Adds the random index.
		moveAntDueToOutOfBounds();
	}

	public void run(BitSet original)
	{
		//Checks to make sure that the ant is not out of bounds of the BitSet.
		if(m_antIndexColumn < 0 || m_antIndexRow < 0)
		{
			moveAntDueToOutOfBounds();
		}
		else if(m_antIndexRow >= m_rowCount || m_antIndexColumn >= m_columnCount)
		{
			moveAntDueToOutOfBounds();
		}

//	If the current ant cell is true.
		if(original.get(indexFinder(m_antIndexRow, m_antIndexColumn)))
		{
			m_ant.toggleCell(indexFinder(m_antIndexRow, m_antIndexColumn));

//	A switch statement to move the ant in a specific direction depending on orientation.
			switch(m_direction)
			{
				case 0:
					--m_antIndexColumn;
					m_direction = 2;
					break;

				case 1:
					--m_antIndexRow;
					m_direction = 0;
					break;

				case 2:
					++m_antIndexRow;
					m_direction = 3;
					break;

				case 3:
					++m_antIndexColumn;
					m_direction = 1;
					break;
			}

		}
//	If the current ant cell is false.
		else
		{
			m_ant.toggleCell(indexFinder(m_antIndexRow, m_antIndexColumn));
// A switch statment that moves the ant in a specific direction depending on orientation.
			switch(m_direction)
			{
				case 0:
					++m_antIndexColumn;
					m_direction = 1;
					break;

				case 1:
					++m_antIndexRow;
					m_direction = 3;
					break;

				case 2:
					--m_antIndexRow;
					m_direction = 0;
					break;

				case 3:
					--m_antIndexColumn;
					m_direction = 2;
					break;
			}
		}
	}

	public int giveAntIndex()
	{
//		Returns the ant index.
	    return indexFinder(m_antIndexRow, m_antIndexColumn);
	}

	public int indexFinder(int row, int column)
	{
//	Returns the 1D index of the 2D row and column.
		return ((row * m_columnCount) + column);
	}

//  This is called if the ant moves out of bounds of the BitSet.
	public void moveAntDueToOutOfBounds()
	{
		m_antIndexRow = m_randInt.nextInt(m_rowCount - 1);
		m_antIndexColumn = m_randInt.nextInt(m_columnCount - 1);
		m_direction = 0;
	}
}
