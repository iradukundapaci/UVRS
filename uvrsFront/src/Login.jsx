import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import Navigation from "./components/Navigation";

function Login() {
	const [email, setEmail] = useState("");
	const [password, setPassword] = useState("");

	const handleLogin = (e) => {
		e.preventDefault();
		// Add your login logic here (e.g., make an API request)
		console.log("Logging in with:", { email, password });
	};

	return (
		<>
			<Navigation />
			<div className="container">
				<div className="row justify-content-center mt-5">
					<div className="col-md-6">
						<Form onSubmit={handleLogin}>
							<Form.Group controlId="formBasicEmail">
								<Form.Label>Email address</Form.Label>
								<Form.Control
									type="email"
									placeholder="Enter email"
									value={email}
									onChange={(e) => setEmail(e.target.value)}
									required
								/>
							</Form.Group>

							<Form.Group controlId="formBasicPassword">
								<Form.Label>Password</Form.Label>
								<Form.Control
									type="password"
									placeholder="Password"
									value={password}
									onChange={(e) =>
										setPassword(e.target.value)
									}
									required
								/>
							</Form.Group>

							<Button variant="primary" type="submit">
								Login
							</Button>
						</Form>
					</div>
				</div>
			</div>
		</>
	);
}

export default Login;
