import React from "react";
import Navigation from "./components/Navigation";
import VehicleCard from "./components/VehicleCard";
import Paginate from "./components/Paginate";
import { useEffect, useState } from "react";
import axios from "axios";
import "./App.css";

const Home = () => {
	const [vehicles, setVehicles] = useState([]);

	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await axios.get(
					"http://localhost:8080/vehicles/all?page=0&pageSize=50"
				);
				setVehicles(response.data.content);
			} catch (error) {
				console.error("Error fetching data:", error);
			}
		};

		fetchData();
	}, []);
	console.log(vehicles);
	return (
		<>
			<Navigation />
			<div id="pageBody">
				<div id="filterBody">Filter</div>
				<div id="vehicleList">
					{vehicles.map((vehicle) => (
						<VehicleCard
							key={vehicle.plateNumber}
							vehicle={vehicle}
						/>
					))}
				</div>
			</div>
			<div id="pagesController">
				<Paginate />
			</div>
		</>
	);
};

export default Home;
