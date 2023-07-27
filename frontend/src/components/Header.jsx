import notesIcon from '../assets/notes-icon.png';
import {Button, Container, Image, Nav, Navbar, NavDropdown, ToggleButton} from "react-bootstrap";
import AddModal from "./AddModal.jsx";
import {useGlobalContext} from "../context.jsx";

const Header = () => {
    const {shownNotesStatus, setShownNotesStatus, NotesStatus} = useGlobalContext();
    return (
        <Navbar collapseOnSelect expand={"lg"} className={"bg-dark"} data-bs-theme={"dark"}>
            <Container>
                <Navbar.Brand href={"/"}><Image src={notesIcon} width={"48"} height={"48"}/>Notes</Navbar.Brand>
                <Navbar.Toggle aria-controls={"responsive-navbar-nav"}/>
                <Navbar.Collapse id={"responsive-navbar-nav"}>
                    <Nav className={"me-auto"}/>
                    <Nav>
                        <ToggleButton
                            className={"me-2"}
                            id="toggle-check"
                            type="checkbox"
                            variant="outline-info"
                            checked={shownNotesStatus === NotesStatus.ARCHIVED}
                            value={NotesStatus.ACTIVE}
                            onChange={(e) =>
                                setShownNotesStatus(e.currentTarget.checked ? NotesStatus.ARCHIVED : NotesStatus.ACTIVE)
                            }
                        >
                            Archive
                        </ToggleButton>
                        <AddModal/>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default Header;