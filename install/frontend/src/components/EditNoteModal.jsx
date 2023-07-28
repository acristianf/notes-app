import {useState} from "react";
import {Button, Modal} from "react-bootstrap";
import EditNoteForm from "./EditNoteForm.jsx";

const EditNoteModal = ({currentNote}) => {
    const [show, setShow] = useState(false);

    function handleClose() {
        setShow(false);
    }

    function handleShow() {
        setShow(true);
    }

    return (
        <>
            <Button variant={"info"} className={"text-black"} onClick={handleShow}>Edit</Button>
            <Modal backdrop={"static"} keyboard={false} show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Note</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <EditNoteForm currentNote={currentNote}/>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant={"outline-dark"} onClick={handleClose}>
                        Close
                    </Button>
                    <Button form={"editNoteForm"} type={"submit"} variant={"info"} className={"text-black"}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}

export default EditNoteModal;