import java.util.BitSet;
import java.util.Random;

public class StepGenerator
{
//	The temporary BitSet that is used to determine the new alive and dead cells.
    private BitSet m_tempData;
//	The columnCount of the grid.
    private int m_columnCount;
//	The rowCount of the grid.
    private int m_rowCount;

    private Random randInt = new Random();

    public StepGenerator(int givenRowCount, int givenColumnCount)
    {
//	The columnCount is set to the columnCount given by the ant class.
    	m_columnCount = givenColumnCount;
//	The rowCount is set to the rowCount given by the ant class.
    	m_rowCount = givenRowCount;
//	The tempData is created using the length finder function and the values passed by the ant class.
    	m_tempData = new BitSet(lengthFinder());
    }

    public int lengthFinder()
    {
//	This returns the grid as a 1D representation.
    	return (m_columnCount * m_rowCount);
    }

    public BitSet generate(BitSet original, int mode)
    {
        if(mode == 1)
        {
            int temp = 0;
            m_tempData = original;

            for(int i = 0; i < original.size(); ++i)
            {
                temp = randInt.nextInt(1000);

                if(temp % 2 == 0)
                {
                    m_tempData.set(i, true);
                }
                else
                {
                    m_tempData.set(i, false);
                }
            }
        }
//	Returns the inital borad layout.
    	return m_tempData;

    }

    private int indexFinder(int row, int column)
    {
//	Returns the index of the 1D location so the coder can keep thinking in 2D.
    	return ((row * m_columnCount) + column);
    }


}
