// Generated code from Butter Knife. Do not modify!
package edu.tequila.tecmm.exampleitny;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DescriptionMovie_ViewBinding implements Unbinder {
  private DescriptionMovie target;

  private View view2131558533;

  @UiThread
  public DescriptionMovie_ViewBinding(DescriptionMovie target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DescriptionMovie_ViewBinding(final DescriptionMovie target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.collapser = Utils.findRequiredViewAsType(source, R.id.collapser, "field 'collapser'", CollapsingToolbarLayout.class);
    target.background = Utils.findRequiredViewAsType(source, R.id.image_background, "field 'background'", ImageView.class);
    target.txtComments = Utils.findRequiredViewAsType(source, R.id.txtComments, "field 'txtComments'", TextView.class);
    target.txtSynopsis = Utils.findRequiredViewAsType(source, R.id.txtSynopsis, "field 'txtSynopsis'", TextView.class);
    view = Utils.findRequiredView(source, R.id.fab_add, "method 'addComments'");
    view2131558533 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addComments();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DescriptionMovie target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.collapser = null;
    target.background = null;
    target.txtComments = null;
    target.txtSynopsis = null;

    view2131558533.setOnClickListener(null);
    view2131558533 = null;
  }
}
