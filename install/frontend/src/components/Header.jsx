import notesIcon from '../assets/notes-icon.png';
import {Container, Image, Nav, Navbar, NavDropdown, ToggleButton} from "react-bootstrap";
import AddModal from "./AddModal.jsx";
import {useGlobalContext} from "../context.jsx";

const Header = () => {
    const {
        categories,
        shownNotesStatus,
        setShownNotesStatus,
        NotesStatus,
        selectedCategory,
        setCategory
    } = useGlobalContext();

    function handleCategorySelection(e) {
        setCategory(e.currentTarget.value);
    }

    return (
        <Navbar collapseOnSelect expand={"lg"} className={"bg-dark"} data-bs-theme={"dark"}>
            <Container>
                <Navbar.Brand className={"pe-3 border-end border-1 border-white"} href={"/"}>
                    <Image src={notesIcon} width={"48"} height={"48"}/> Notes
                </Navbar.Brand>
                <Navbar.Toggle aria-controls={"responsive-navbar-nav"}/>
                <Navbar.Collapse id={"responsive-navbar-nav"}>
                    <select className={"form-select w-25 form-select-lg text-white"}
                            value={selectedCategory}
                            onChange={handleCategorySelection}>
                        <option id={"noCat"} value={""}>No Category</option>
                        {categories.map(cat => (
                                <option key={cat.id} id={cat.id}>
                                    {cat.name}
                                </option>
                            )
                        )}
                    </select>
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