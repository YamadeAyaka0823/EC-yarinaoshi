package com.example.domain;

import java.util.Arrays;

public class WeatherHacks {
	
	public Location location;
	
	public Forecasts[] forecasts;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Forecasts[] getForecasts() {
		return forecasts;
	}

	public void setForecasts(Forecasts[] forecasts) {
		this.forecasts = forecasts;
	}

	@Override
	public String toString() {
		return "WeatherHacks [location=" + location + ", forecasts=" + Arrays.toString(forecasts) + "]";
	}
	
	

}
