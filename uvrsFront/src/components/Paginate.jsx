import Pagination from "react-bootstrap/Pagination";

function Paginate() {
	return (
		<Pagination>
			<Pagination.First />
			<Pagination.Prev />
			<Pagination.Item active>{12}</Pagination.Item>
			<Pagination.Next />
			<Pagination.Last />
		</Pagination>
	);
}

export default Paginate;
