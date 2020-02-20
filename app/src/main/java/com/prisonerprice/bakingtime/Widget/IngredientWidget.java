package com.prisonerprice.bakingtime.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.prisonerprice.bakingtime.DetailScreen.DetailViewModel;
import com.prisonerprice.bakingtime.MainScreen.MainActivity;
import com.prisonerprice.bakingtime.Model.RecipeUtils;
import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidget extends AppWidgetProvider {

    private static final String TAG = IngredientWidget.class.getSimpleName();
    private static final String GOING_TO_MAIN_SCREEN = "going_to_main_screen";
    private static DetailViewModel model = DetailViewModel.getInstance();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // set dataString
        String dataString = "Go to select a recipe!";
        if (model.getRecipe() != null) {
            Step ingredients = RecipeUtils.parseIngredientsToStep(model.getRecipe().getIngredients());
            dataString = model.getRecipe().getRecipeName() + "\n" + "\n" +
                    ingredients.getDescription();
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget);
        views.setTextViewText(R.id.widget_text, dataString);

        Intent homeIntent = new Intent(context, MainActivity.class);
        homeIntent.setData(Uri.parse(homeIntent.toUri(Intent.URI_INTENT_SCHEME)));
        homeIntent.setAction(GOING_TO_MAIN_SCREEN);
        PendingIntent homePendingIntent = PendingIntent.getActivity(context, 0, homeIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.widget_select_btn, homePendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate is called!");
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {
            Log.d(TAG, "onReceive is called");
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName thisAppWidget = new ComponentName(context.getPackageName(), IngredientWidget.class.getName());
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
    }


}

