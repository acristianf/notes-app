import {Form} from "react-bootstrap";
import {useState} from "react";
import {useGlobalContext} from "../context.jsx";

const EditNoteForm = ({currentNote}) => {
    const [note, setNote] = useState({...currentNote})

    const [validated, setValidated] = useState(false);
    const {notesUrl, editNote} = useGlobalContext();

    function handleSubmit(e) {
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
        } else {
            editNote(notesUrl + "/" + note.id, note);
        }
        setValidated(true);
    }

    function handleChange(e) {
        const name = e.currentTarget.name;
        setNote({...note, [name]: e.currentTarget.value});
    }

    return (
        <Form id={"editNoteForm"} noValidate validated={validated} onSubmit={handleSubmit}>
            <Form.Group className="mb-3" controlId="editNoteForm.titleArea">
                <Form.Label>Title</Form.Label>
                <Form.Control
                    onChange={handleChange}
                    type="text"
                    autoFocus
                    required
                    maxLength={50}
                    name={"title"}
                    value={note.title}
                />
            </Form.Group>
            <Form.Group
                className="mb-3"
                controlId="editNoteForm.bodyTextArea"
            >
                <Form.Label>Note body</Form.Label>
                <Form.Control
                    onChange={handleChange}
                    as="textarea"
                    rows={3}
                    required
                    maxLength={2000}
                    name={"body"}
                    value={note.body}
                />
            </Form.Group>
        </Form>
    )
}

export default EditNoteForm;
