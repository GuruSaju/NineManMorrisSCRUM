/**
 * 
 */
package morris_java;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Coord  implements Serializable{
	int x;
	int y;
	
    public Coord(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}


	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("x = %d, y = %d", this.x, this.y);
	}
	
	public String toStringDebug() {
		return String.format("(%d, %d)", this.x, this.y);
	}
}
