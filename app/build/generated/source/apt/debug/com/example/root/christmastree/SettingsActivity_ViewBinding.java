// Generated code from Butter Knife. Do not modify!
package com.example.root.christmastree;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingsActivity_ViewBinding implements Unbinder {
  private SettingsActivity target;

  private View view2131230842;

  @UiThread
  public SettingsActivity_ViewBinding(SettingsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingsActivity_ViewBinding(final SettingsActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.httpInput = Utils.findRequiredViewAsType(source, R.id.http_edit, "field 'httpInput'", TextInputEditText.class);
    target.portInput = Utils.findRequiredViewAsType(source, R.id.port_edit, "field 'portInput'", TextInputEditText.class);
    target.passwordLayout = Utils.findRequiredViewAsType(source, R.id.port_layout, "field 'passwordLayout'", TextInputLayout.class);
    target.loginLayout = Utils.findRequiredViewAsType(source, R.id.http_layout, "field 'loginLayout'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.save_button, "method 'onLoginButtonClicked'");
    view2131230842 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.httpInput = null;
    target.portInput = null;
    target.passwordLayout = null;
    target.loginLayout = null;

    view2131230842.setOnClickListener(null);
    view2131230842 = null;
  }
}
