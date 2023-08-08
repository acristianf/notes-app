import {Button, Modal} from "react-bootstrap";
import {useState} from "react";
import AddNoteForm from "./AddNoteForm.jsx";

const AddModal = () => {
    const [show, setShow] = useState(false);

    function handleClose() {
        setShow(false);
    }

    function handleShow() {
        setShow(true);
    }

    return (
        <>
            <Button variant={"info"} className={"text-black"} onClick={handleShow}>Add Note</Button>
            <Modal backdrop={"static"} keyboard={false} show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Note</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddNoteForm/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant={"outline-dark"} onClick={handleClose}>
                        Close
                    </Button>
                    <Button form={"addNoteForm"} type={"submit"} variant={"info"} className={"text-black"}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default AddModal;
