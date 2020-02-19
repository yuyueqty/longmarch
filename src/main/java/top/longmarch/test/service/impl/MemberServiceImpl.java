package top.longmarch.test.service.impl;

import top.longmarch.test.entity.Member;
import top.longmarch.test.dao.MemberDao;
import top.longmarch.test.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-19
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements IMemberService {

}
