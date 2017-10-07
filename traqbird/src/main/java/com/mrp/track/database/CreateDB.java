package com.mrp.track.database;

public class CreateDB {
	
	public void createtables()
	{
		String statement ="";
		
		statement = "create table public.org_details( org_id varchar(20), org_name varchar(7), address varchar(200), city varchar(40), state varchar(40), country varchar(40), pincode varchar(10), name varchar(50), phone varchar(20), email varchar(70) )";
		
		statement = "create table public.admin_details(org_id varchar(20) REFERENCES public.org_details(org_id), name varchar(50), email varchar(50), mobile varchar(20) PRIMARY KEY, password varchar(50), verified integer)";
		
		statement = "create table public.user_details(name varchar(50), email varchar(50), mobile varchar(20), device_type varchar(10), reg_id varchar(256), PRIMARY KEY(email,mobile))";
		
		statement = "create table public.bus_details(org_id varchar(20) REFERENCES public.org_details(org_id), bus_id varchar(20), reg_no varchar(20), make varchar(50), capacity integer, available integer, PRIMARY KEY(org_id,bus_id))";
		
		statement = "create table public.driver_details(org_id varchar(20) REFERENCES public.org_details(org_id), driver_id varchar(20), name varchar(50), mobile varchar(20), address varchar(200), licence varchar(30), PRIMARY KEY(org_id,driver_id))";
		
		statement = "create table public.route(org_id varchar(20) REFERENCES public.org_details(org_id), route_id varchar(20), PRIMARY KEY(org_id,route_id))";
		
		statement = "create table public.route_details(org_id varchar(20), route_id varchar(20), ppname varchar(40), pplat varchar(20), pplong varchar(20), time varchar(20), FOREIGN KEY(org_id,route_id) REFERENCES route(org_id,route_id))";
		
		statement = "create table public.emp_details(org_id varchar(20) REFERENCES public.org_details(org_id), emp_id varchar(20), name varchar(50), mobile varchar(20), PRIMARY KEY(org_id,emp_id))";
		
		statement = "create table public.emp_mapping(org_id varchar(20), emp_id varchar(20), route_id varchar(50), FOREIGN KEY(org_id,emp_id) REFERENCES emp_details(org_id,emp_id), FOREIGN KEY(org_id,route_id) REFERENCES route(org_id,route_id))";
		
		statement = "create table public.tracker(gps_id varchar(40) PRIMARY KEY, lat varchar(20), lon varchar(20), time varchar(20))";
		
		statement = "create table public.mapper(org_id varchar(20), bus_id varchar(20), route_id varchar(20), driver_id varchar(20), gps_id varchar(40) REFERENCES tracker(gps_id), FOREIGN KEY(org_id,route_id) REFERENCES route(org_id,route_id), FOREIGN KEY(org_id,bus_id) REFERENCES bus_details(org_id,bus_id), FOREIGN KEY(org_id,driver_id) REFERENCES driver_details(org_id,driver_id), PRIMARY KEY(org_id,bus_id,gps_id))";
		
		statement = "create table public.otp(email varchar(50), mobile varchar(20), otp varchar(20), FOREIGN KEY(email,mobile) REFERENCES user_details(email,mobile))";
		
		statement = statement+"";
	}
	
}
