package com.prisonerprice.bakingtime.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.prisonerprice.bakingtime.DetailScreen.DetailViewModel;
import com.prisonerprice.bakingtime.Model.RecipeUtils;
import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientWidget extends AppWidgetProvider {

    private static final String TAG = IngredientWidget.class.getSimpleName();
    private static DetailViewModel model = DetailViewModel.getInstance();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String text) {

        // set dataString
        Log.d(TAG, "Update something!");
        String dataString = "Go to select a recipe!";
        if (model.getRecipe() != null) {
            Step ingredients = RecipeUtils.parseIngredientsToStep(model.getRecipe().getIngredients());
            dataString = ingredients.getDescription();
        }

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget);
        views.setTextViewText(R.id.widget_text, dataString);

        Intent intentUpdate = new Intent(context, IngredientWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        /*
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, idArray);

         */

        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context, appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        views.setOnClickPendingIntent(R.id.widget_btn, pendingUpdate);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateWidgets(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String text) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, text);
        }
    }
}

