// Generated by view binder compiler. Do not edit!
package com.example.clothessuggester.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.clothessuggester.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class WeatherItemBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView date;

  @NonNull
  public final View recyclerViewIntervals;

  @NonNull
  public final TextView weatherDegreeMax;

  @NonNull
  public final TextView weatherDegreeMin;

  @NonNull
  public final ImageView weatherIcon;

  private WeatherItemBinding(@NonNull ConstraintLayout rootView, @NonNull TextView date,
      @NonNull View recyclerViewIntervals, @NonNull TextView weatherDegreeMax,
      @NonNull TextView weatherDegreeMin, @NonNull ImageView weatherIcon) {
    this.rootView = rootView;
    this.date = date;
    this.recyclerViewIntervals = recyclerViewIntervals;
    this.weatherDegreeMax = weatherDegreeMax;
    this.weatherDegreeMin = weatherDegreeMin;
    this.weatherIcon = weatherIcon;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static WeatherItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WeatherItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.weather_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WeatherItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.date;
      TextView date = ViewBindings.findChildViewById(rootView, id);
      if (date == null) {
        break missingId;
      }

      id = R.id.recycler_view_intervals;
      View recyclerViewIntervals = ViewBindings.findChildViewById(rootView, id);
      if (recyclerViewIntervals == null) {
        break missingId;
      }

      id = R.id.weather_degree_max;
      TextView weatherDegreeMax = ViewBindings.findChildViewById(rootView, id);
      if (weatherDegreeMax == null) {
        break missingId;
      }

      id = R.id.weather_degree_min;
      TextView weatherDegreeMin = ViewBindings.findChildViewById(rootView, id);
      if (weatherDegreeMin == null) {
        break missingId;
      }

      id = R.id.weather_icon;
      ImageView weatherIcon = ViewBindings.findChildViewById(rootView, id);
      if (weatherIcon == null) {
        break missingId;
      }

      return new WeatherItemBinding((ConstraintLayout) rootView, date, recyclerViewIntervals,
          weatherDegreeMax, weatherDegreeMin, weatherIcon);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
