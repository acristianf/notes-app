import {useContext, useEffect, useRef, useState} from "react";
import React from "react";
import API from './api/axiosConfig.jsx';

const AppContext = React.createContext();

const notesUrl = "/api/notes"
const categoriesUrl = "api/categories";

const NotesStatus = {
    ARCHIVED: "ARCHIVED",
    ACTIVE: "ACTIVE"
}

const AppProvider = ({children}) => {
    const [notes, setNotes] = useState([]);
    const [shownNotesStatus, setShownNotesStatus] = useState(NotesStatus.ACTIVE);

    const postNote = (url, note) => {
        API.post(url, note)
            .then(res => console.log(res))
            .catch(e => console.log(e.message));
    }

    const fetchNotes = (url) => {
        API.get(url)
            .then(res => setNotes(res.data))
            .catch(e => console.log(e.message));
    }

    const updateNote = (url) => {
        API.put(url)
            .then(res => console.log(res.status))
            .catch(e => console.log(e.message));
    }

    const deleteNote = (url) => {
        API.delete(url)
            .then(res => console.log(res.status))
            .catch(e => console.log(e.message));
    }

    // TODO: Finish editNote
    const editNote = (url, note) => {
        API.put(url, note)
            .then(res => console.log(res))
            .catch(e => console.log(e.message))
    }

    useEffect(() => {
        fetchNotes(notesUrl);
    }, [])

    return (
        <AppContext.Provider value={{
            notes,
            setNotes,
            notesUrl,
            shownNotesStatus,
            setShownNotesStatus,
            NotesStatus,
            fetchNotes,
            updateNote,
            postNote,
            deleteNote,
            editNote
        }}>{children}</AppContext.Provider>
    )
};

export const useGlobalContext = () => {
    return useContext(AppContext);
};

export {AppContext, AppProvider};