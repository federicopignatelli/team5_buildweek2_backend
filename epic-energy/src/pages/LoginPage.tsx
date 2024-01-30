import { Col, Container, Row } from "react-bootstrap";
import LoginFormComponent from "../components/LoginFormComponent";

export const LoginPage = () => {
   return (
      <>
         <Container>
            <Row>
               <h1>EPIC ENERGY</h1>
            </Row>
            <Row>
               <Col>
                  <LoginFormComponent />
               </Col>
            </Row>
         </Container>
      </>
   );
};
