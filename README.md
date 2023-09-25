
# SJ 23/24 - POS 2.Klasse Aufgabestellung - ChatGPT-Clone with DB Integrateion

## Implementing a ChatGPT Clone Using JavaScript, the OpenAI API, and Database Integration

### Objective
Create a ChatGPT clone by using JavaScript and the OpenAI API, as demonstrated in the provided video. Additionally, integrate database functionality to store chat histories and provide access via a REST API using the Spring framework. Write a comprehensive documentation detailing the implementation process, including screenshots, to allow others to reproduce and understand your work.

### Task Description
In this project, you will be required to implement a ChatGPT clone using JavaScript and the OpenAI API. You are to follow the steps outlined in the provided video, which serves as a basis for the implementation.

- **Database Integration**: All chat histories should be stored in a database. The access to this database should be facilitated using JPA with Hibernate as the ORM tool.
- **REST API**: The client should be able to access the Entity classes via a REST API. This API should be provided using the Spring framework.
- As you implement the project, you are expected to maintain a written documentation to illustrate the implementation process.

### Group Work
This project is to be carried out in groups of two. Members of each group will work together, with one person programming and the other documenting the implementation process. The roles should be alternated periodically so that both group members have the opportunity to contribute to both the programming and documentation aspects of the project. This collaborative approach will not only enhance your teamwork skills but also ensure that both members are fully engaged and have a comprehensive understanding of the entire project.

Remember to clearly indicate which member of the group performed which tasks in the documentation. This helps in assessing individual contributions and understanding of the project.

### Requirements
1. **Implementation**: Follow the video instructions to implement the ChatGPT clone using JavaScript and the OpenAI API. Your implementation should be in the form of a web-based application that allows users to interact with the ChatGPT clone.
2. **Database Integration**: Implement the necessary database structures and integrate them into your application to store chat histories.
3. **REST API**: Develop a REST API using the Spring framework to provide access to the chat histories stored in the database.
4. **Documentation**: You are required to document the entire process of implementing the ChatGPT clone and the additional features, in Markdown format. The documentation should be detailed and should provide explanations for the steps taken, as well as the significance of the code lines used.
    - **Explanations**: Your documentation should explain why each step is necessary and how it contributes to the overall implementation.
    - **Screenshots**: Provide screenshots at various stages of the implementation process. These will serve as a visual aid and provide a point of reference to confirm the correctness of the implementation.
5. **PDF Version**: Convert the Markdown documentation into a PDF format for easy distribution and reading.

### Deliverables
1. **Implementation**: Provide your completed implementation, sharing the forked Git-Repository.
2. **Documentation**: Submit your written documentation in Markdown format, including all the relevant screenshots.
3. **PDF Version**: Submit a PDF version of the documentation.

### Development Tips
- **Recommended IDE's**: For the development of the client, Webstorm represents a suitable IDE. For the backend development (RestAPI + JPA + Hibernate), IntelliJ or Eclipse are suitable.
- **Spring Boot Starters**: Consider using Spring Boot Starters for quick setup of JPA, Hibernate, and REST API functionalities.
- **Database Choice**: For development purposes, consider using an in-memory database like H2. It's easy to set up and integrates well with Spring Boot.
- **Postman**: Use Postman or a similar tool to test your REST API endpoints before integrating them into your application.
- **Error Handling**: Ensure that your REST API has proper error handling. This will make debugging easier and provide meaningful feedback to the client.
- **OpenAI API Documentation**: Thoroughly read the OpenAI API documentation to understand rate limits, request formats, and best practices.
- **Annotations**: Familiarize yourself with common Spring and JPA annotations like `@RestController`, `@Entity`, `@Autowired`, etc. They play a crucial role in the functionality of your application.
- **Tutorials**: There are numerous tutorials and courses available online for Spring Boot, JPA, Hibernate, and REST API development. Consider referring to them if you get stuck.

