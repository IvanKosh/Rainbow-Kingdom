package com.binarnahata.rainbowkingdom.Libs.DataSaver;

import android.support.annotation.Nullable;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * RainbowKingdom
 * Created on 23.12.15, 9:51
 *
 * @author bat
 * @version 1.0
 */
public interface DataSaver {
	<T> void  saveData(ArrayList<T> list);
	<T> ArrayList<T> loadData(Class<T> object);
}