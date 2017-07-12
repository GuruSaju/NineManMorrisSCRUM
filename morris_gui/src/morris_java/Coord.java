/**
 * 
 */
package morris_java;

/**
 * @author speng
 * created on Oct 6, 2015
 */
public class Coord {
	int x;
	int y;
	
	
    public Coord(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return x*100 + y;
	}


	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Coord))
            return false;
        if (obj == this)
            return true;

        Coord rhs = (Coord) obj;
        return this.x == rhs.x && this.y == rhs.y;
    }
}
