package sdz.chapitreCinq;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

public class LabyrintheView extends SurfaceView implements SurfaceHolder.Callback {

	Boule mBoule;
	public Boule getBoule() {
		return mBoule;
	}

	public void setBoule(Boule pBoule) {
		this.mBoule = pBoule;
	}

	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;

	private List<Bloc> mBlocks = null;
	public List<Bloc> getBlocks() {
		return mBlocks;
	}

	public void setBlocks(List<Bloc> pBlocks) {
		this.mBlocks = pBlocks;
	}

	Paint mPaint;

	public LabyrintheView(Context pContext) {
		super(pContext);
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();

		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);

		mBoule = new Boule();

	}

	@Override
	protected void onDraw(Canvas pCanvas) {
		// Dessiner le fond de l'écran en premier
		//pCanvas.drawColor(Color.CYAN);
		pCanvas.drawColor(Color.GRAY);

		if(mBlocks != null) {
			// Dessiner tous les blocs du labyrinthe
			for(Bloc b : mBlocks) {
				switch(b.getType()) {
				case DEPART:
					mPaint.setColor(Color.WHITE);
					break;
				case ARRIVEE:
					mPaint.setColor(Color.RED);
					break;
				case TROU:
					mPaint.setColor(Color.BLACK);
					break;
				}
				//Commenté
				//pCanvas.drawRect(b.getRectangle(), mPaint);

				//AJoutéé
				if(b.getType() == Bloc.Type.ARRIVEE) {
					//pCanvas.drawCircle(mBoule.getX(), mBoule.getY(), Boule.RAYON, mPaint);
					//pCanvas.drawRect(b.getRectangle(), mPaint);
					pCanvas.drawCircle(b.getRectangle().centerX(),b.getRectangle().centerY(),Boule.RAYON*3,mPaint);
				}
				else
				{
					pCanvas.drawRect(b.getRectangle(), mPaint);
				}

			}
		}

		// Dessiner la boule
		if(mBoule != null) {
			mPaint.setColor(mBoule.getCouleur());
			pCanvas.drawCircle(mBoule.getX(), mBoule.getY(), Boule.RAYON, mPaint);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder pHolder, int pFormat, int pWidth, int pHeight) {
		//
	}

	@Override
	public void surfaceCreated(SurfaceHolder pHolder) {
		mThread.keepDrawing = true;
		mThread.start();
		// Quand on crée la boule, on lui indique les coordonnées de l'écran
		if(mBoule != null ) {
			this.mBoule.setHeight(getHeight());
			this.mBoule.setWidth(getWidth());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder pHolder) {
		mThread.keepDrawing = false;
		boolean retry = true;
		while (retry) {
			try {
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {}
		}
		
	}


	private class DrawingThread extends Thread {
		boolean keepDrawing = true;

		@Override
		public void run() {
			Canvas canvas;
			while (keepDrawing) {
				canvas = null;

				try {
					canvas = mSurfaceHolder.lockCanvas();
					synchronized (mSurfaceHolder) {
						onDraw(canvas);
					}
				} finally {
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}

				// Pour dessiner à 50 fps
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {}
			}
		}
	}
}
