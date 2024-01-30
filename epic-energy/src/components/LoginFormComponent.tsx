import { Button } from "react-bootstrap";
import Form from "react-bootstrap/Form";

function LoginFormComponent() {
   return (
      <Form>
         <Form.Group className="mb-3" controlId="formGroupEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="email" placeholder="Enter email" />
         </Form.Group>
         <Form.Group className="mb-3" controlId="formGroupPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" />
         </Form.Group>
         <Button variant="primary" type="submit">
            Submit
         </Button>
      </Form>
   );
}

export default LoginFormComponent;
