import React, { useEffect, useState } from "react";
import axios from "axios";
import VehicleCard from "./VehicleCard";

const VehicleList = () => {
	let vehicles = [];

	useEffect(() => {
		const fetchData = async () => {
			try {
				const response = await axios.get(
					"http://localhost:8080/vehicles/all?page=0&pageSize=10"
				);
				vehicles = response.data.content;
			} catch (error) {
				console.error("Error fetching data:", error);
			}
		};

		fetchData();
	}, []);

	return (
		<>
			{vehicles.map((vehicle) => (
				<p>test</p>
			))}
		</>
	);
};

export default VehicleList;
