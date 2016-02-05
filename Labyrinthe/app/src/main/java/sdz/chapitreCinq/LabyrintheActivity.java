package sdz.chapitreCinq;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class LabyrintheActivity extends Activity {
	// Identifiant de la boîte de dialogue de victoire
	public static final int VICTORY_DIALOG = 0;
	// Identifiant de la boîte de dialogue de défaite
	public static final int DEFEAT_DIALOG = 1;

	// Le moteur graphique du jeu
	private LabyrintheView mView = null;
	// Le moteur physique du jeu
	private LabyrintheEngine mEngine = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mView = new LabyrintheView(this);
		setContentView(mView);

		mEngine = new LabyrintheEngine(this);

		Boule b = new Boule();
		mView.setBoule(b);
		mEngine.setBoule(b);

		List<Bloc> mList = mEngine.buildLabyrinthe();
		mView.setBlocks(mList);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mEngine.resume();
	} 

	@Override
	protected void onPause() {
		super.onStop();
		mEngine.stop();
	}

	@Override
	public Dialog onCreateDialog (int id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch(id) {
		case VICTORY_DIALOG:
			builder.setCancelable(false)
			.setMessage("Bienvenue sur cette ampoule LIFI")
			.setTitle("AMPOULE LIFI TROUVEE")
			.setNeutralButton("retourner", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// L'utilisateur peut recommencer s'il le veut
					//mEngine.reset();
					mEngine.resume();
				}
			});
			break;

		case DEFEAT_DIALOG:
			builder.setCancelable(false)
			.setMessage("Impossible de traverser un mur")
			.setTitle("Deplacement non autorise")
			.setNeutralButton("retourner", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					//mEngine.reset();
					mEngine.stop();
					//mEngine.resume();
				}
			});
		}
		return builder.create();
	}

	@Override
	public void onPrepareDialog (int id, Dialog box) {
		// A chaque fois qu'une boîte de dialogue est lancée, on arrête le moteur physique
		mEngine.stop();
	}
}