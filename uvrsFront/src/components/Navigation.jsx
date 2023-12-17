import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { Link } from "react-router-dom";

function Navigation() {
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
						<Form className="d-flex">
							<Form.Control
								type="search"
								placeholder="Search"
								className="me-2"
								aria-label="Search"
							/>
							<Button variant="outline-success">Search</Button>
						</Form>
						<Nav
							className="my-2 my-lg-0"
							style={{ maxHeight: "100px" }}
							navbarScroll
						>
							<Link to={"/login"}>Login</Link>
							<Link to={"/signup"}>Signup</Link>
						</Nav>
					</Navbar.Collapse>
				</Container>
			</Navbar>
		</>
	);
}

export default Navigation;
