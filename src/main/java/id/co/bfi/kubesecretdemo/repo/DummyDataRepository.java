package id.co.bfi.kubesecretdemo.repo;

import id.co.bfi.kubesecretdemo.model.DummyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyDataRepository extends JpaRepository<DummyData, Long>, JpaSpecificationExecutor<DummyData> {
}
