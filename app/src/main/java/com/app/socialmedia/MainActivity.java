package com.app.socialmedia;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    ClipboardManager clipboard;
    ClipData clipData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clipboard = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.addPrimaryClipChangedListener(mPrimaryChangeListener);

        textView = findViewById(R.id.article_text);

        textView.setCustomSelectionActionModeCallback(new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                menu.clear();
                mode.getMenuInflater().inflate(R.menu.custome_menu, menu);
                menu.removeItem(android.R.id.selectAll);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.share) {

                int min = 0;
                int max = textView.getText().length();
                if (textView.isFocused()) {
                    final int selStart = textView.getSelectionStart();
                    final int selEnd = textView.getSelectionEnd();

                    min = Math.max(0, Math.min(selStart, selEnd));
                    max = Math.max(0, Math.max(selStart, selEnd));
                }
                // Perform your definition lookup with the selected text
                final CharSequence selectedText = textView.getText()
                        .subSequence(min, max);
                String text = selectedText.toString();

                clipData = ClipData.newPlainText("text", text);
                clipboard.setPrimaryClip(clipData);

            Intent intent = new Intent(MainActivity.this , EditingActivity.class);
            startActivity(intent);
            mode.finish();
            return true;
            }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
    }

    ClipboardManager.OnPrimaryClipChangedListener mPrimaryChangeListener = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {

            // this will be called whenever you copy something to the clipboard
            Log.i("ABC", "onPrimaryClipChanged: " );

            Intent intent = new Intent(MainActivity.this , EditingActivity.class);
            startActivity(intent);
        }
    };
}
