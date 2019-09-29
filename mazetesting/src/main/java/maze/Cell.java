package maze;

public class Cell {
	private boolean _isSolid;
	private boolean _isChecked;
	private int _r;
	private int _c;
	private boolean isEnd;
	private boolean isStart;
	
	public Cell(boolean solid, int r, int c) {
		super();
		this._isSolid = solid;
		this._isChecked = false;
		this._c = c;
		this._r = r;
		this.isEnd = false;
		this.isStart = false;
	}

	public boolean isSolid() {
		return _isSolid;
	}

	public void setSolidState(boolean _isPath) {
		this._isSolid = _isPath;
	}

	public boolean isChecked() {
		return _isChecked;
	}

	public void setIsChecked(boolean _isChecked) {
		this._isChecked = _isChecked;
	}
	
	public int getRow() {
		return _r;
	}
	
	public int getCol() {
		return _c;
	}
	
	public boolean isEnd() {
		return isEnd;
	}

	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	@Override
	public String toString() {
		return "[" + this._r + ", " + this._c + ", " + this._isSolid + "]";
	}
	
}
