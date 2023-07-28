import {Form,} from "react-bootstrap";
import {useState} from "react";
import {useGlobalContext} from "../context.jsx";
import NoteCategoryControl from "./NoteCategoryControl.jsx";

const AddNoteForm = () => {
    const [note, setNote] = useState({
        title: "",
        body: "",
        categories: []
    })

    const [validated, setValidated] = useState(false);
    const {notesUrl, postNote} = useGlobalContext();

    function handleSubmit(e) {
        const form = e.currentTarget;
        if (form.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
        } else {
            postNote(notesUrl, note);
        }
        setValidated(true);
    }

    function handleChange(e) {
        const name = e.currentTarget.name;
        setNote({...note, [name]: e.currentTarget.value});
    }

    return (
        <>
            <Form id={"addNoteForm"} noValidate validated={validated} onSubmit={handleSubmit}>
                <Form.Group className="mb-3" controlId="noteForm.titleArea">
                    <Form.Label>Title</Form.Label>
                    <Form.Control
                        onChange={handleChange}
                        type="text"
                        placeholder="Note Title"
                        autoFocus
                        required
                        maxLength={50}
                        name={"title"}
                    />
                </Form.Group>
                <Form.Group
                    className="mb-3"
                    controlId="noteForm.bodyTextArea"
                >
                    <Form.Label>Note body</Form.Label>
                    <Form.Control onChange={handleChange} as="textarea" rows={3} required maxLength={2000}
                                  name={"body"}/>
                </Form.Group>
            </Form>
            <NoteCategoryControl note={note} setNote={setNote}/>
        </>
    )
}

export default AddNoteForm;