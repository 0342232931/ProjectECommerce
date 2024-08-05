package vn.ths.e_commerce.ECommerce.service.serviceInterfaceIpm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.ths.e_commerce.ECommerce.entity.Voucher;
import vn.ths.e_commerce.ECommerce.repository.VoucherRepository;
import vn.ths.e_commerce.ECommerce.service.serviceInterface.VoucherService;

import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceIpm implements VoucherService {
    private final VoucherRepository voucherRepository;

    @Autowired
    public VoucherServiceIpm(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    @Transactional
    public Voucher add(Voucher voucher) {
        return voucherRepository.saveAndFlush(voucher);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        voucherRepository.deleteById(id);
    }

    @Override
    public List<Voucher> getAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher getById(Integer id) {
        return voucherRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Voucher update(Voucher voucher) {
        Optional<Voucher> optionalProductDetail = voucherRepository.findById(voucher.getId());
        if(optionalProductDetail.isPresent()){
            return voucherRepository.saveAndFlush(voucher);
        }
        return null;
    }

}
