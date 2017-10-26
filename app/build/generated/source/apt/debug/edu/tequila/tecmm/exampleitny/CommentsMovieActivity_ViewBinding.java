// Generated code from Butter Knife. Do not modify!
package edu.tequila.tecmm.exampleitny;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CommentsMovieActivity_ViewBinding implements Unbinder {
  private CommentsMovieActivity target;

  private View view2131558525;

  @UiThread
  public CommentsMovieActivity_ViewBinding(CommentsMovieActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CommentsMovieActivity_ViewBinding(final CommentsMovieActivity target, View source) {
    this.target = target;

    View view;
    target.title = Utils.findRequiredViewAsType(source, R.id.txtTitleComments, "field 'title'", TextView.class);
    target.txtComment = Utils.findRequiredViewAsType(source, R.id.txtComment, "field 'txtComment'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btnAdd, "method 'addComment'");
    view2131558525 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.addComment();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentsMovieActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.txtComment = null;

    view2131558525.setOnClickListener(null);
    view2131558525 = null;
  }
}
