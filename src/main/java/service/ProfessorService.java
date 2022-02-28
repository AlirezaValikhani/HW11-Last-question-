package service;

import model.Professor;
import repository.ProfessorRepository;

import java.sql.Connection;
import java.util.List;

public class ProfessorService implements BaseService<Professor> {
    private Connection connection;
    private ProfessorRepository professorRepository;

    public ProfessorService(Connection connection) {
        this.connection = connection;
        this.professorRepository = new ProfessorRepository(this.connection);
    }

    @Override
    public Integer save(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public Professor read(Professor professor) {
        return professorRepository.read(professor);
    }

    public Professor readIdByUserName(String userName) {
        Professor professor = new Professor(userName);
        return professorRepository.readIdByUserName(professor);
    }

    public Professor readByUsername(String userName) {
        Professor professor = new Professor(userName);
        return professorRepository.readByUsername(professor);
    }

    @Override
    public List<Professor> readAll() {
        return professorRepository.readAll();
    }

    @Override
    public void update(Professor professor) {
        professorRepository.update(professor);
    }

    @Override
    public void delete(Professor professor) {
        professorRepository.delete(professor);
    }
}
