package com.hraczynski.isitworthtravellingapp.client.pojos.weatherforecast;

import java.util.List;

public class DailyItem{
	private int moonset;
	private double rain;
	private int sunrise;
	private Temp temp;
	private double moonPhase;
	private double uvi;
	private int moonrise;
	private int pressure;
	private int clouds;
	private FeelsLike feelsLike;
	private double windGust;
	private int dt;
	private double pop;
	private int windDeg;
	private double dewPoint;
	private double snow;
	private int sunset;
	private List<WeatherItem> weather;
	private int humidity;
	private double windSpeed;

	public int getMoonset(){
		return moonset;
	}

	public double getRain(){
		return rain;
	}

	public int getSunrise(){
		return sunrise;
	}

	public Temp getTemp(){
		return temp;
	}

	public double getMoonPhase(){
		return moonPhase;
	}

	public double getUvi(){
		return uvi;
	}

	public int getMoonrise(){
		return moonrise;
	}

	public int getPressure(){
		return pressure;
	}

	public int getClouds(){
		return clouds;
	}

	public FeelsLike getFeelsLike(){
		return feelsLike;
	}

	public double getWindGust(){
		return windGust;
	}

	public int getDt(){
		return dt;
	}

	public double getPop(){
		return pop;
	}

	public int getWindDeg(){
		return windDeg;
	}

	public double getDewPoint(){
		return dewPoint;
	}

	public double getSnow(){
		return snow;
	}

	public int getSunset(){
		return sunset;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public int getHumidity(){
		return humidity;
	}

	public double getWindSpeed(){
		return windSpeed;
	}
}