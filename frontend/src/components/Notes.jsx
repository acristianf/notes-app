import {Button, Card, Container} from "react-bootstrap";
import {useGlobalContext} from "../context.jsx";
import DeleteModal from "./DeleteModal.jsx";
import EditNoteModal from "./EditNoteModal.jsx";

const Notes = () => {
    const {notesUrl, notes, setNotes, updateNote, deleteNote, shownNotesStatus, NotesStatus} = useGlobalContext();

    function updateNotesArray(updatedNote, status) {
        updatedNote.status = status;
        const updated = notes.map(n => {
            if (updatedNote.id === n.id) {
                return updatedNote;
            } else {
                return n;
            }
        })
        setNotes(updated);
    }

    function handleArchive(note) {
        updateNote(notesUrl + "/" + note.id + "/archive");
        updateNotesArray(note, NotesStatus.ARCHIVED);
    }

    function handleActivate(note) {
        updateNote(notesUrl + "/" + note.id + "/activate");
        updateNotesArray(note, NotesStatus.ACTIVE);
    }

    function handleDelete(note) {
        deleteNote(notesUrl + "/" + note.id);
        const updated = notes.filter(n => n.id !== note.id);
        setNotes(updated);
    }

    return (
        <Container className={"justify-content-center p-0 mt-1 card-collection"}>
            {
                notes
                    .filter(note => note.status === shownNotesStatus)
                    .map((note, idx) => {
                        return (
                            <Card key={note.id} id={note.id}
                                  className={"border-black m-1 mt-1 p-0 bg-light-subtle text-dark rounded-0 h-90"}>
                                <Card.Body>
                                    <Card.Title>{note.title}</Card.Title>
                                    <Card.Subtitle className={"text-muted"}>
                                        {note.lastEdited != null ? "Last edited: " + note.lastEdited : "Created at: " + note.createdAt}
                                    </Card.Subtitle>
                                    <Card.Text>{note.body}</Card.Text>
                                </Card.Body>
                                <Card.Footer className={"d-flex justify-content-end"}>
                                    {
                                        shownNotesStatus === NotesStatus.ACTIVE
                                            ? <Button variant={"info"} className={"text-black me-1"}
                                                      onClick={() => handleArchive(note)}>Archive</Button>
                                            : <Button variant={"info"} className={"text-black me-1"}
                                                      onClick={() => handleActivate(note)}>Activate</Button>
                                    }
                                    <div className={"me-1"}>
                                        <EditNoteModal currentNote={note}/>
                                    </div>
                                    {/*<Button variant={"info"} className={"text-black me-1"}>Edit</Button>*/}
                                    <DeleteModal handleDelete={handleDelete} note={note}/>
                                </Card.Footer>
                            </Card>
                        )
                    })
            }
        </Container>
    )
}

export default Notes;
