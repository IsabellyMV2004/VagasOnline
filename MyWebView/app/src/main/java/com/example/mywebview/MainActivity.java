package com.example.mywebview;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private boolean isBack = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // React precisa de JS
        webSettings.setSupportZoom(false);

        // Use 10.0.2.2 para acessar o localhost da sua máquina pelo emulador Android
        webView.loadUrl("http://10.0.2.2:3000/");

        // Ajuste na navegação — permitir o domínio local
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                isBack = false;
                String host = request.getUrl().getHost();
                if (host != null && (host.contains("10.0.2.2") || host.contains("localhost"))) {
                    return false; // Permite abrir URLs do próprio front
                }
                return true; // Bloqueia navegação para fora
            }
        });

        // Controle do botão voltar
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    if (isBack) finish();
                    Snackbar snackbar = Snackbar.make(webView, "Pressione novamente para finalizar", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    isBack = true;
                }
            }
        });
    }
}