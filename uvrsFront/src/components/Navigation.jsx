import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";
import React, { useState } from "react";

function Navigation({ onSearchChange }) {
	const handleSearchChange = (event) => {
		const value = event.target.value;
		onSearchChange(value);
	};

	const handleSearchSubmit = (event) => {
		event.preventDefault();
		console.log(event);
	};
	return (
		<>
			<Navbar expand="lg" className="bg-body-tertiary">
				<Container fluid>
					<Navbar.Brand href="#" className="col-md-5">
						Navbar scroll
					</Navbar.Brand>
					<Navbar.Toggle aria-controls="navbarScroll" />
					<Navbar.Collapse
						id="navbarScroll"
						className="col-md-6 d-flex flex-row justify-content-between"
					>
						<Form className="d-flex" onSubmit={handleSearchSubmit}>
							<Form.Control
								type="search"
								placeholder="Search"
								className="me-2"
								aria-label="Search"
								onChange={handleSearchChange}
							/>
							<Button variant="outline-success" type="submit">
								Search
							</Button>
						</Form>
						<Nav
							className="my-2 my-lg-0"
							style={{ maxHeight: "100px" }}
							navbarScroll
						>
							<Link
								to={"/login"}
								style={{
									textDecoration: "none",
									margin: "0 10px 0 0",
								}}
							>
								Login
							</Link>
							<Link
								to={"/signup"}
								style={{
									textDecoration: "none",
									margin: "0 10px 0 0",
								}}
							>
								Signup
							</Link>
						</Nav>
					</Navbar.Collapse>
				</Container>
			</Navbar>
		</>
	);
}

export default Navigation;
