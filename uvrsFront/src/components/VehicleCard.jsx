import Button from "react-bootstrap/Button";
import Card from "react-bootstrap/Card";
import Modal from "react-bootstrap/Modal";
import { useState } from "react";

function MyVerticallyCenteredModal(props) {
	const vehicle = props.vehicle;
	return (
		<Modal
			{...props}
			size="lg"
			aria-labelledby="contained-modal-title-vcenter"
			centered
		>
			<Modal.Header closeButton>
				<Modal.Title id="contained-modal-title-vcenter">
					{/* Info on {vehicle.brandName} {vehicle.vehicleModel} */}
				</Modal.Title>
			</Modal.Header>
			<Modal.Body>
				{/* Year: {vehicle.yearOfRealease}
				Owner: {vehicle.vehicleOwner}
				vehicle-status:{vehicle.vehicleStatus} */}
			</Modal.Body>
			<Modal.Footer>
				<Button onClick={props.onHide}>Close</Button>
			</Modal.Footer>
		</Modal>
	);
}

function VehicleCard(vehicle) {
	const [modalShow, setModalShow] = useState(false);

	return (
		<Card style={{ width: "16rem", margin: "0 10px 10px 0" }}>
			<Card.Img variant="top" src="holder.js/100px180" />
			<Card.Body>
				<Card.Title>
					{vehicle.vehicle.brandName} {vehicle.vehicle.vehicleModel}
				</Card.Title>
				<Card.Text>
					<p>vehicle-type:{vehicle.vehicle.type.vehicleType}</p>
					<p>vehicle-status:{vehicle.vehicle.vehicleStatus}</p>
				</Card.Text>
				<Button variant="primary" onClick={() => setModalShow(true)}>
					Get more Info
				</Button>

				<MyVerticallyCenteredModal
					show={modalShow}
					onHide={() => setModalShow(false)}
					vehicle={vehicle.vehicle}
				/>
			</Card.Body>
		</Card>
	);
}

export default VehicleCard;
