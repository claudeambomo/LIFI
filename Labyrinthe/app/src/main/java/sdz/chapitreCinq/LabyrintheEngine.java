package sdz.chapitreCinq;

import java.util.ArrayList;
import java.util.List;

import sdz.chapitreCinq.Bloc.Type;
import android.app.Service;
import android.graphics.RectF;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class LabyrintheEngine {
	private Boule mBoule = null;
	public Boule getBoule() {
		return mBoule;
	}

	public void setBoule(Boule pBoule) {
		this.mBoule = pBoule;
	}

	// Le labyrinthe
	private List<Bloc> mBlocks = null;

	private LabyrintheActivity mActivity = null;

	private SensorManager mManager = null;
	private Sensor mAccelerometre = null;

	//Ajoute
	

	SensorEventListener mSensorEventListener = new SensorEventListener() {

		@Override
		public void onSensorChanged(SensorEvent pEvent) {
			float x = pEvent.values[0];
			float y = pEvent.values[1];


			if(mBoule != null) {
				// On met � jour les coordonn�es de la boule
				RectF hitBox = mBoule.putXAndY(x, y);

				// Pour tous les blocs du labyrinthe
				for(Bloc block : mBlocks) {
					// On crée un nouveau rectangle pour ne pas modifier celui du bloc
					RectF inter = new RectF(block.getRectangle());
					if(inter.intersect(hitBox)) {
						// On agit différement en fonction du type de bloc
						switch(block.getType()) {
						case TROU:
							//mActivity.showDialog(LabyrintheActivity.DEFEAT_DIALOG);
                            if (block.getRectangle().contains(10 , 10)){
                                //mBoule.reset();
                                mBoule.reset();
                            }
							mBoule.putXAndY(hitBox.centerX() -2*hitBox.centerX(), hitBox.centerY() - 2*hitBox.centerX());
                            //mBoule.putXAndY()
                            //mBoule.reset();
                            //System.out.println (hitBox.centerX() + "et" + hitBox.centerY() );
							break;


						case DEPART:
							break;

						case ARRIVEE:
							mActivity.showDialog(LabyrintheActivity.VICTORY_DIALOG);
							break;
						}
						break;
					}
				}
			}
		}

		@Override
		public void onAccuracyChanged(Sensor pSensor, int pAccuracy) {

		}
	};

	public LabyrintheEngine(LabyrintheActivity pView) {
		mActivity = pView;
		mManager = (SensorManager) mActivity.getBaseContext().getSystemService(Service.SENSOR_SERVICE);
		mAccelerometre = mManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	// Remet à zéro l'emplacement de la boule
	public void reset() {
		mBoule.reset();
	}

	// Arrête le capteur
	public void stop() {
		mManager.unregisterListener(mSensorEventListener, mAccelerometre);
	}

	// Redémarre le capteur
	public void resume() {
		mManager.registerListener(mSensorEventListener, mAccelerometre, SensorManager.SENSOR_DELAY_GAME);
	}

	// Construit le labyrinthe
	public List<Bloc> buildLabyrinthe() {
		mBlocks = new ArrayList<Bloc>();

        //Le premier paramètre représente la hauteur et le deuxième représente la largeur du trait
		for (int counter = 0; counter < 27; counter++) {
			mBlocks.add(new Bloc(Type.TROU, 0, counter));

		}
        for (int counter = 0; counter < 27; counter++) {
            mBlocks.add(new Bloc(Type.TROU, 55, counter));

        }
		//Ajout d'un bloc rouge
        for (int counter = 6; counter <7; counter++) {
            mBlocks.add(new Bloc(Type.ARRIVEE, 4, counter));

        }
        /*for (int counter = 4; counter <5; counter++) {
            for (int counter2 = 2; counter2 <3; counter2++) {
                mBlocks.add(new Bloc(Type.ARRIVEE, counter, counter2));
            }
        }*/

        //Début de la ligne 12
        for (int counter = 0; counter < 3; counter++) {
            mBlocks.add(new Bloc(Type.TROU, 12, counter));
        }
        /*for (int counter = 10; counter < 15; counter++) {    //Hauteur numero 15
            mBlocks.add(new Bloc(Type.TROU, 12, counter));
        }*/

        /*for (int counter = 0; counter < 3; counter++) {
            mBlocks.add(new Bloc(Type.TROU, 15, counter));
        }*/


        for (int counter = 0; counter < 15; counter++) {
            mBlocks.add(new Bloc(Type.TROU, counter, 0));
            mBlocks.add(new Bloc(Type.TROU, counter, 27));

        }
        for (int counter = 0; counter < 28; counter++) {
            mBlocks.add(new Bloc(Type.TROU, counter, 15));

        }
        for (int counter = 33; counter < 39; counter++) {
            mBlocks.add(new Bloc(Type.TROU, counter, 15));

        }
        for (int counter = 20; counter < 35; counter++) {
            mBlocks.add(new Bloc(Type.TROU, counter, 0));
            mBlocks.add(new Bloc(Type.TROU, counter, 27));

        }
        for (int counter = 40; counter < 56; counter++) {
            mBlocks.add(new Bloc(Type.TROU, counter, 0));
            mBlocks.add(new Bloc(Type.TROU, counter, 27));

        }


		Bloc b = new Bloc(Type.DEPART, 2, 2);
        mBoule.setInitialRectangle(new RectF(b.getRectangle()));
		mBlocks.add(b);

		//mBlocks.add(new Bloc(Type.ARRIVEE, 8, 11));

		/*Ajoute*/
		//mBlocks.add(new Bloc(Type.ARRIVEE, 25, 2));



		return mBlocks;
	}

}
