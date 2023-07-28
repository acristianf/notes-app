import {Button, Card, Container} from "react-bootstrap";
import {useGlobalContext} from "../context.jsx";
import DeleteModal from "./DeleteModal.jsx";
import EditNoteModal from "./EditNoteModal.jsx";
import NoteCard from "./NoteCard.jsx";

const Notes = () => {
    const {
        notesUrl,
        notes,
        setNotes,
        updateNote,
        deleteNote,
        shownNotesStatus,
        NotesStatus,
        selectedCategory
    } = useGlobalContext();

    function updateNotesStatusInArray(updatedNote, status) {
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
        updateNotesStatusInArray(note, NotesStatus.ARCHIVED);
    }

    function handleActivate(note) {
        updateNote(notesUrl + "/" + note.id + "/activate");
        updateNotesStatusInArray(note, NotesStatus.ACTIVE);
    }

    function handleDelete(note) {
        deleteNote(notesUrl + "/" + note.id);
        const updated = notes.filter(n => n.id !== note.id);
        setNotes(updated);
    }

    return (
        <main>
            <Container className={"border-bottom border-2 border-dark mt-1 fs-3 text-dark"}>
                {shownNotesStatus} NOTES
            </Container>
            <Container className={"justify-content-center p-0 mt-1 card-collection"}>
                {
                    notes
                        .filter(note => (
                            (selectedCategory !== ""
                                ? note.categories.find(cat => cat === selectedCategory)
                                : true)
                            && note.status === shownNotesStatus
                        ))
                        .map(note => {
                            return (
                                <NoteCard key={note.id}
                                          note={note}
                                          NotesStatus={NotesStatus}
                                          shownNotesStatus={shownNotesStatus}
                                          handleArchive={handleArchive}
                                          handleActivate={handleActivate}
                                          handleDelete={handleDelete}
                                />
                            )
                        })
                }
            </Container>
        </main>
    )
}

export default Notes;
