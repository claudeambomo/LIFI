package sdz.chapitreCinq;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by CLAUDE on 08/02/2016.
 */
public class accueil extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_layout);

        final Button loginButton = (Button) findViewById(R.id.okButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accueil.this, LabyrintheActivity.class);
                startActivity(intent);
            }
        });
    }
}