// Generated by view binder compiler. Do not edit!
package com.example.onlineauctionsystem.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.onlineauctionsystem.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySignUpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText editTextTextEmailAddress;

  @NonNull
  public final EditText editTextTextPassword;

  @NonNull
  public final EditText editTextTextPersonName;

  @NonNull
  public final ImageView imageView;

  @NonNull
  public final Button register;

  @NonNull
  public final TextView textView;

  private ActivitySignUpBinding(@NonNull ConstraintLayout rootView,
      @NonNull EditText editTextTextEmailAddress, @NonNull EditText editTextTextPassword,
      @NonNull EditText editTextTextPersonName, @NonNull ImageView imageView,
      @NonNull Button register, @NonNull TextView textView) {
    this.rootView = rootView;
    this.editTextTextEmailAddress = editTextTextEmailAddress;
    this.editTextTextPassword = editTextTextPassword;
    this.editTextTextPersonName = editTextTextPersonName;
    this.imageView = imageView;
    this.register = register;
    this.textView = textView;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySignUpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_sign_up, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySignUpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.editTextTextEmailAddress;
      EditText editTextTextEmailAddress = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextEmailAddress == null) {
        break missingId;
      }

      id = R.id.editTextTextPassword;
      EditText editTextTextPassword = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPassword == null) {
        break missingId;
      }

      id = R.id.editTextTextPersonName;
      EditText editTextTextPersonName = ViewBindings.findChildViewById(rootView, id);
      if (editTextTextPersonName == null) {
        break missingId;
      }

      id = R.id.imageView;
      ImageView imageView = ViewBindings.findChildViewById(rootView, id);
      if (imageView == null) {
        break missingId;
      }

      id = R.id.register;
      Button register = ViewBindings.findChildViewById(rootView, id);
      if (register == null) {
        break missingId;
      }

      id = R.id.textView;
      TextView textView = ViewBindings.findChildViewById(rootView, id);
      if (textView == null) {
        break missingId;
      }

      return new ActivitySignUpBinding((ConstraintLayout) rootView, editTextTextEmailAddress,
          editTextTextPassword, editTextTextPersonName, imageView, register, textView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}