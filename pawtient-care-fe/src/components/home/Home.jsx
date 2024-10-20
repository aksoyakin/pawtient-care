import d5 from "../../assets/images/d5.jpg"
import vett from "../../assets/images/vett.jpg"
import {Button, Card, Col, Container, ListGroup, Row} from 'react-bootstrap';

const Home = () => {
    return (
        <Container className={"home-container mt-5"}>
            <Row>
                <Col md={6} className={"mb-3"}>
                    <Card>
                        <Card.Img
                            variant="top"
                            src={d5}
                            alt="About Us"
                            className={"hero-image"}
                        />
                        <Card.Body>
                            <h2 className="text-info">Who We Are</h2>
                            <Card.Title>Comprehensive care for your furry friends.</Card.Title>

                            <Card.Text>
                                Pets are more than just companions; they are cherished members of our families who
                                deserve the best care possible.
                                Comprehensive care for your furry friends encompasses a holistic approach that addresses
                                their physical, emotional, and social needs.
                                This includes regular veterinary check-ups, proper nutrition tailored to their specific
                                requirements, and ample exercise to keep them healthy and happy.
                            </Card.Text>

                            <Card.Text>
                                Additionally, mental stimulation through play and socialization is crucial for their
                                overall well-being.
                                By prioritizing their care, we not only enhance their quality of life but also
                                strengthen the bond we share with them,
                                ensuring they thrive in a loving and supportive environment.
                            </Card.Text>

                            <Card.Text>
                                To further enrich the lives of our pets, its essential to recognize the importance of
                                preventive care and early intervention. Regular screenings can help identify potential
                                health issues before they become serious, allowing for timely treatment and better
                                outcomes. Moreover, fostering an environment filled with love and attention helps
                                alleviate stress and anxiety, contributing to their emotional well-being. Engaging in
                                training and enrichment activities not only boosts their confidence but also enhances
                                their behavior, making for a more harmonious household. By being proactive in their care
                                and addressing their individual needs, we ensure our furry friends lead vibrant,
                                fulfilling lives by our side.Additionally, creating a routine that includes playtime,
                                training, and quiet moments can help establish a sense of security for our pets.
                                Ultimately, investing in their overall well-being not only benefits them but also
                                enriches our own lives, as we share countless joyful moments together.
                            </Card.Text>

                            <Button variant={"outline-info"}>Meet Our Veterinarians</Button>
                        </Card.Body>
                    </Card>
                </Col>


                <Col md={6} className={"mb-3"}>
                    <Card>
                        <Card.Img
                            variant="top"
                            src={vett}
                            alt="About Us"
                            className={"hero-image"}
                        />
                        <Card.Body>
                            <h2 className={'text-info'}>Our Services</h2>
                            <Card.Title>What We Do</Card.Title>

                            <ListGroup className="services-list">
                                <ListGroup.Item>Veterinary Check-ups</ListGroup.Item>
                                <ListGroup.Item>Emergency Surgery</ListGroup.Item>
                                <ListGroup.Item>Pet Vaccinations</ListGroup.Item>
                                <ListGroup.Item>Dental Care</ListGroup.Item>
                                <ListGroup.Item>Spaying and Neutering</ListGroup.Item>
                                <ListGroup.Item>And many more...</ListGroup.Item>
                            </ListGroup>

                            <Card.Text className={'mt-3'}>
                                At our veterinary clinic, we are dedicated to providing exceptional care for your pets
                                at every stage of their lives. Our services include routine wellness exams,
                                vaccinations, and dental care to ensure your furry friends stay healthy and happy. We
                                also offer advanced diagnostics, surgical procedures, and emergency care for when your
                                pets need extra attention. Beyond medical treatment, we prioritize education and support
                                for pet owners, guiding you in nutrition, behavior, and preventive care. Our
                                compassionate team is committed to creating a nurturing environment where your pets
                                receive the highest quality of care and love.
                            </Card.Text>

                            <Button variant={"outline-info"}>Meet Our Veterinarians</Button>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>

            <div className={'card mb-5'}>
                <h4>
                    What people are saying about {" "}
                    <span className={'text-info'}>Pawtient Care</span> Veterinarians
                </h4>

                <hr/>

                <p className={"text-center"}>Here, we are going to be sliding veterinarians across.</p>
            </div>
        </Container>
    );
};

export default Home;