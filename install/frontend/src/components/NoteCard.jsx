import {Button, Card} from "react-bootstrap";
import EditNoteModal from "./EditNoteModal.jsx";
import DeleteModal from "./DeleteModal.jsx";

const NoteCard = ({note, shownNotesStatus, NotesStatus, handleArchive, handleActivate, handleDelete}) => {
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
}

export default NoteCard;