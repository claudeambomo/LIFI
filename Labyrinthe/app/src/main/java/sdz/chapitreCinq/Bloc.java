package sdz.chapitreCinq;

import android.graphics.RectF;

public class Bloc {
	enum  Type { TROU, DEPART, ARRIVEE };
	
	private float SIZE = Boule.RAYON * 2;
	
	private Type mType = null;
	private RectF mRectangle = null;
	
	public Type getType() {
		return mType;
	}

	public RectF getRectangle() {
		return mRectangle;
	}

	public Bloc(Type pType, int pX, int pY) {
		this.mType = pType;
		this.mRectangle = new RectF(pX * SIZE, pY * SIZE, (pX + 3/2) * SIZE, (pY + 3/2) * SIZE);
	}
}