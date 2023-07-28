import {Button, Modal} from "react-bootstrap";
import {useState} from "react";

const DeleteModal = ({handleDelete, note}) => {
    const [show, setShow] = useState(false);

    function handleClose() {
        setShow(false);
    }

    function handleShow() {
        setShow(true);
    }

    return (
        <>
            <Button variant={"info"} className={"text-black"} onClick={handleShow}>Remove Note</Button>
            <Modal backdrop={"static"} keyboard={false} show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Remove Note</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Are you sure you want to delete this note?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant={"outline-dark"} onClick={handleClose}>
                        Cancel
                    </Button>
                    <Button onClick={() => handleDelete(note)} variant={"info"} className={"text-black"}>
                        Confirm
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default DeleteModal;
