import {Button, CloseButton, Col, Form, InputGroup, ListGroup, ListGroupItem, Row} from "react-bootstrap";
import {useState} from "react";

const NoteCategoryControl = ({note, setNote}) => {
    const [validateCategory, setValidateCategory] = useState(false);
    const [categoryToAdd, setCategoryToAdd] = useState("")
    function handleAddCategory(e) {
        const form = e.currentTarget.form;
        if (form.checkValidity() === false) {
            e.preventDefault();
            e.stopPropagation();
        } else {
            note.categories.indexOf(categoryToAdd) === -1
                ? setNote({...note, categories: [...note.categories, categoryToAdd]})
                : null;
        }
        setValidateCategory(true);
    }

    function handleRemoveCategory(cat) {
        setNote({...note, categories: note.categories.filter(c => c !== cat)})
    }

    return (
        <>
            <Form noValidate onSubmit={e => e.preventDefault()} id={"addNoteCategoryForm"} validated={validateCategory}>
                <Form.Label>Categories</Form.Label>
                <ListGroup className={"category-group mb-2"}>
                    {
                        note.categories.map(cat => (<ListGroupItem key={cat}>
                            <Row className={"p-0"}>
                                <Col className={"col-10"}>{cat}</Col>
                                <Col><CloseButton onClick={() => handleRemoveCategory(cat)}/></Col>
                            </Row>
                        </ListGroupItem>))
                    }
                </ListGroup>
                <Row>
                    <InputGroup>
                        <Col className={"col-10 pe-2"}>
                            <Form.Control id={"categoryForm.addCategoryText"}
                                          placeholder={"Category name"}
                                          type="text"
                                          onChange={(e) => setCategoryToAdd(e.currentTarget.value)}
                                          required
                                          maxLength={50}/>
                        </Col>
                        <Col>
                            <Button form={"addNoteCategoryForm"} variant={"info"}
                                    onClick={handleAddCategory}>Add</Button>
                        </Col>
                    </InputGroup>
                </Row>
            </Form>
        </>
    )
}

export default NoteCategoryControl;