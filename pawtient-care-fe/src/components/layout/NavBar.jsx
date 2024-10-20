import React from 'react';
import {Container, Nav, Navbar, NavDropdown} from "react-bootstrap";
import {Link} from "react-router-dom";

const NavBar = () => {
    return (
        <Navbar expand={"lg"} sticky="top" className="nav-bg">
            <Container>
                <Navbar.Brand to={"/"} as={Link} className={"nav-home"}>
                    PawtientCare
                </Navbar.Brand>

                <Navbar.Toggle aria-controls="responsive-navbar-nav"/>
                <Navbar.Collapse id="responsive-navbar-nav"/>

                <Nav className="me-auto">
                    <Nav.Link to={"/doctors"} as={Link}>
                        Meet Our Veterinarians
                    </Nav.Link>

                    <Nav.Link to={"/admin-dashboard"} as={Link}>
                        Admin
                    </Nav.Link>
                </Nav>

                <Nav>
                    <NavDropdown title={"Account"} id={"basic-nav-dropdown"}>

                        <NavDropdown.Item to={"/user-dashboard"} as={Link}>
                            Register
                        </NavDropdown.Item>

                        <NavDropdown.Divider/>

                        <NavDropdown.Item to={"/user-dashboard"} as={Link}>
                            Login
                        </NavDropdown.Item>

                        <NavDropdown.Divider/>

                        <NavDropdown.Item to={"/user-dashboard"} as={Link}>
                            My Dashboard
                        </NavDropdown.Item>

                        <NavDropdown.Divider/>

                        <NavDropdown.Item to={"/logout"} as={Link}>
                            Logout
                        </NavDropdown.Item>

                    </NavDropdown>
                </Nav>
            </Container>
        </Navbar>
    );
};

export default NavBar;