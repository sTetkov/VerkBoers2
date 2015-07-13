package messages;

import java.io.Serializable;

public class Pair<X, Y> implements Serializable {
	public final X left;
	public final Y right;

	public Pair(X left, Y right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		String res = "";
		if (left == null)
			res += "NULL ";
		else
			res += left.toString() + " ";
		if (right == null)
			res += "NULL";
		else
			res += right.toString();
		return res;
	}
}
