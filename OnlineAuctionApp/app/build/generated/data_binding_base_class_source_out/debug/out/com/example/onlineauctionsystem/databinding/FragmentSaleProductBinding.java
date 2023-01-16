// Generated by view binder compiler. Do not edit!
package com.example.onlineauctionsystem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.onlineauctionsystem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentSaleProductBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final ConstraintLayout layout;

  @NonNull
  public final ListView listview;

  @NonNull
  public final EditText searchTxt;

  @NonNull
  public final ImageButton searchView;

  private FragmentSaleProductBinding(@NonNull FrameLayout rootView,
      @NonNull ConstraintLayout layout, @NonNull ListView listview, @NonNull EditText searchTxt,
      @NonNull ImageButton searchView) {
    this.rootView = rootView;
    this.layout = layout;
    this.listview = listview;
    this.searchTxt = searchTxt;
    this.searchView = searchView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSaleProductBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSaleProductBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_sale_product, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSaleProductBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.layout;
      ConstraintLayout layout = ViewBindings.findChildViewById(rootView, id);
      if (layout == null) {
        break missingId;
      }

      id = R.id.listview;
      ListView listview = ViewBindings.findChildViewById(rootView, id);
      if (listview == null) {
        break missingId;
      }

      id = R.id.search_txt;
      EditText searchTxt = ViewBindings.findChildViewById(rootView, id);
      if (searchTxt == null) {
        break missingId;
      }

      id = R.id.searchView;
      ImageButton searchView = ViewBindings.findChildViewById(rootView, id);
      if (searchView == null) {
        break missingId;
      }

      return new FragmentSaleProductBinding((FrameLayout) rootView, layout, listview, searchTxt,
          searchView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
