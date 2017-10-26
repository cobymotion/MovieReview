// Generated code from Butter Knife. Do not modify!
package edu.tequila.tecmm.exampleitny;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AdapterRecyclerSimple$ViewHolderRecycler_ViewBinding implements Unbinder {
  private AdapterRecyclerSimple.ViewHolderRecycler target;

  @UiThread
  public AdapterRecyclerSimple$ViewHolderRecycler_ViewBinding(AdapterRecyclerSimple.ViewHolderRecycler target,
      View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.movieName, "field 'name'", TextView.class);
    target.avatar = Utils.findRequiredViewAsType(source, R.id.thumbnail, "field 'avatar'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AdapterRecyclerSimple.ViewHolderRecycler target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.avatar = null;
  }
}
